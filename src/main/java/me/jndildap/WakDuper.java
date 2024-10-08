package me.jndildap;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.*;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WakDuper implements ClientModInitializer {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final Config config = new Config();
    public static Text restoreScreenBind;
    public static int txtColor = 0xFF828282;
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeClient() {
        // Console text on game close
        ClientLifecycleEvents.CLIENT_STOPPING.register(this::onShutdownClient);

        // Console text on game open
        LOGGER.info("\033[38;2;50;205;50mwakDuper Successfully Initialized!\033[0m");

        // Register keybinding for restoring the saved screen
        KeyBinding restoreScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("wakduper.key.restoreScreen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "wakDuper"));

        // When the keybind is pressed
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            assert client.player != null;
            restoreScreenBind = restoreScreenKey.getBoundKeyLocalizedText();

            if (restoreScreenKey.wasPressed() && Variables.storedScreen != null && Variables.storedScreenHandler != null) {
                client.setScreen(Variables.storedScreen);
                client.player.currentScreenHandler = Variables.storedScreenHandler;
            }
        });
    }

    public void onShutdownClient(MinecraftClient ignoredClient) {
        LOGGER.info("\033[38;2;50;205;50mShutting Down wakDuper...\033[0m");
        LOGGER.info("\033[38;2;50;205;50mwakDuper Successfully Shut Down!\033[0m");
    }

    // Slot Overlay
    public static void addText(DrawContext context, TextRenderer textRenderer, MinecraftClient client, int x, int y) {
        if(client.player == null) return;
        for(final Slot slot : client.player.currentScreenHandler.slots) {
            final Text id = Text.literal(Integer.toString(slot.id));
            context.drawText(textRenderer, id,
                    slot.x + x + 16 / 2 - textRenderer.getWidth(id) / 2,
                    slot.y + y + 16 / 2 - textRenderer.fontHeight / 2,
                    txtColor, false);
        }
    }

    // Sync ID & Revision
    public static void renderTexts(DrawContext context, TextRenderer textRenderer, MinecraftClient client) {
        if(client.player == null) return;
        final Text syncId = Text.literal("Sync Id: " + client.player.currentScreenHandler.syncId),
            revision = Text.literal("Revision: " + client.player.currentScreenHandler.getRevision());
        context.drawText(textRenderer, syncId, LayoutPos.windowIdX(syncId), LayoutPos.windowIdY(true), -1, false);
        context.drawText(textRenderer, revision, LayoutPos.windowIdX(revision), LayoutPos.windowIdY(false), -1, false);
    }

    // Layout Mode Option
    public static final SimpleOption<LayoutMode> format = new SimpleOption<>("wakduper.format", SimpleOption.constantTooltip(Text.translatable("wakduper.format.tooltip")), (optionText, value) ->
            Text.translatable(value.getTranslationKey()),
            new SimpleOption.AlternateValuesSupportingCyclingCallbacks<>(
                    Arrays.asList(LayoutMode.values()),
                    Stream.of(LayoutMode.values()).collect(Collectors.toList()),
                    mc::isRunning,
                    SimpleOption::setValue,
                    Codec.INT.xmap(LayoutMode::byId, LayoutMode::getId)),
            config.getLayoutMode(), value -> {
                config.setLayout(value);
                config.save();
    });

    // Slot Overlay Option
    public static final SimpleOption<Boolean> overlay = SimpleOption.ofBoolean("wakduper.overlay", SimpleOption.constantTooltip(Text.translatable("wakduper.overlay.tooltip")), config.getOverlayValue(), value -> {
        config.setOverlayValue(value);
        config.save();
    });

    // Copy Json Option
    public static final SimpleOption<Boolean> copyJson = SimpleOption.ofBoolean("wakduper.json", SimpleOption.constantTooltip(Text.translatable("wakduper.json.tooltip")), config.shouldCopyJson(), value -> {
        config.setCopyJson(value);
        config.save();
    });


}
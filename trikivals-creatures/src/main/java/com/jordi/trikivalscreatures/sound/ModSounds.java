package com.jordi.trikivalscreatures.sound;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.creatures.pejelagarto.PejelagartoSounds;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent EASTER_EGG_AMBIENT = ModSounds.registerSoundEvent("easter_egg_ambient");
    public static final SoundEvent EASTER_EGG_DEATH = ModSounds.registerSoundEvent("easter_egg_death");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(TrikiValsCreatures.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static boolean checkEasterEggSound(Entity entity) {
        Text text = entity.getCustomName();
        return text != null && text.getString().equals("TrikiVals_");
    }

    public static void registerSounds() {
        TrikiValsCreatures.LOGGER.info("Registering Sounds for " + TrikiValsCreatures.MOD_ID);

        PejelagartoSounds.registerSounds();
    }
}

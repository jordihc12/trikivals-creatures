package com.jordi.trikivalscreatures.sound;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.pejelagarto.PejelagartoSounds;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(TrikiValsCreatures.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        TrikiValsCreatures.LOGGER.info("Registering Sounds for " + TrikiValsCreatures.MOD_ID);

        PejelagartoSounds.registerSounds();
    }
}

package com.jordi.trikivalscreatures.entity.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class PejelagartoSounds {

    public static final SoundEvent PEJELAGARTO_DEATH_SOUND = ModSounds.registerSoundEvent("pejelagarto_death");
    public static final SoundEvent PEJELAGARTO_HURT_SOUND = ModSounds.registerSoundEvent("pejelagarto_hurt");

    public static void registerSounds() {
        TrikiValsCreatures.LOGGER.info("Registering Sounds for Pejelagarto entity!");
    }
}

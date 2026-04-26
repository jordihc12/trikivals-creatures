package com.jordi.trikivalscreatures.entity.creatures.pejelagarto;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class PejelagartoSounds {

    public static final SoundEvent PEJELAGARTO_IDLE_SOUND = ModSounds.registerSoundEvent("pejelagarto_ambient");
    public static final SoundEvent PEJELAGARTO_ATTACK_SOUND = ModSounds.registerSoundEvent("pejelagarto_attack");
    public static final SoundEvent PEJELAGARTO_DEATH_SOUND = ModSounds.registerSoundEvent("pejelagarto_death");
    public static final SoundEvent PEJELAGARTO_HURT_SOUND = ModSounds.registerSoundEvent("pejelagarto_hurt");

    public static final SoundEvent PEJELAGARTO_IDLE_SOUND_PINK = ModSounds.registerSoundEvent("pejelagarto_ambient_pink");
    public static final SoundEvent PEJELAGARTO_ATTACK_SOUND_PINK = ModSounds.registerSoundEvent("pejelagarto_attack_pink");
    public static final SoundEvent PEJELAGARTO_DEATH_SOUND_PINK = ModSounds.registerSoundEvent("pejelagarto_death_pink");
    public static final SoundEvent PEJELAGARTO_HURT_SOUND_PINK = ModSounds.registerSoundEvent("pejelagarto_hurt_pink");

    public static final SoundEvent PEJELAGARTO_IDLE_SOUND_ALBINO = ModSounds.registerSoundEvent("pejelagarto_ambient_albino");
    public static final SoundEvent PEJELAGARTO_ATTACK_SOUND_ALBINO = ModSounds.registerSoundEvent("pejelagarto_attack_albino");
    public static final SoundEvent PEJELAGARTO_DEATH_SOUND_ALBINO = ModSounds.registerSoundEvent("pejelagarto_death_albino");
    public static final SoundEvent PEJELAGARTO_HURT_SOUND_ALBINO = ModSounds.registerSoundEvent("pejelagarto_hurt_albino");

    public static void registerSounds() {
        TrikiValsCreatures.LOGGER.info("Registering Sounds for Pejelagarto entity!");
    }
}

package com.jordi.trikivalscreatures.entity.palgaster.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Palgaster geometry and animations, loaded once and shared by every Palgaster.
 * Files (exported from Palgaster.bbmodel): models/entity/palgaster.mesh and palgaster.anim.
 * Coordinates are Blockbench pixels (y up, front = -Z).
 */
public final class PalgasterMesh {
    public static final class Bone {
        public final String name;
        public final int parent;          // index in bones, -1 for the root
        public final float px, py, pz;    // pivot
        Bone(String name, int parent, float px, float py, float pz) {
            this.name = name; this.parent = parent; this.px = px; this.py = py; this.pz = pz;
        }
    }

    public static final class Part {
        public final String name;
        public final int bone;
        public final boolean saddle;
        /** per triangle vertex: x, y, z, u, v, nx, ny, nz */
        public final float[] data;
        Part(String name, int bone, boolean saddle, float[] data) {
            this.name = name; this.bone = bone; this.saddle = saddle; this.data = data;
        }
    }

    public static final class Anim {
        public final float length;
        public final boolean loop;
        public final int samples;
        /** [bone][sample * 6 + (rx, ry, rz, px, py, pz)], rotations in degrees */
        public final float[][] tracks;
        Anim(float length, boolean loop, int samples, float[][] tracks) {
            this.length = length; this.loop = loop; this.samples = samples; this.tracks = tracks;
        }
    }

    public final List<Bone> bones = new ArrayList<>();
    public final List<Part> parts = new ArrayList<>();
    public final Map<String, Anim> anims = new HashMap<>();

    private static PalgasterMesh instance;

    public static synchronized PalgasterMesh get() {
        if (instance == null) {
            instance = new PalgasterMesh();
            try {
                instance.load();
            } catch (IOException e) {
                TrikiValsCreatures.LOGGER.error("Could not load the Palgaster model", e);
            }
        }
        return instance;
    }

    private static DataInputStream open(String file) throws IOException {
        InputStream in = PalgasterMesh.class.getResourceAsStream("/assets/" + TrikiValsCreatures.MOD_ID + "/models/entity/" + file);
        if (in == null) {
            throw new IOException("Missing " + file);
        }
        return new DataInputStream(new BufferedInputStream(in, 1 << 16));
    }

    private void load() throws IOException {
        Map<String, Integer> boneIndex = new HashMap<>();
        try (DataInputStream in = open("palgaster.mesh")) {
            in.readInt(); // magic
            in.readInt(); // version
            int boneCount = in.readInt();
            for (int i = 0; i < boneCount; i++) {
                String name = in.readUTF();
                String parent = in.readUTF();
                float px = in.readFloat(), py = in.readFloat(), pz = in.readFloat();
                bones.add(new Bone(name, parent.isEmpty() ? -1 : boneIndex.get(parent), px, py, pz));
                boneIndex.put(name, i);
            }
            int partCount = in.readInt();
            for (int i = 0; i < partCount; i++) {
                String name = in.readUTF();
                String bone = in.readUTF();
                boolean saddle = in.readByte() == 1;
                int tris = in.readInt();
                float[] data = new float[tris * 3 * 8];
                for (int k = 0; k < data.length; k++) {
                    data[k] = in.readFloat();
                }
                parts.add(new Part(name, boneIndex.getOrDefault(bone, 1), saddle, data));
            }
        }
        try (DataInputStream in = open("palgaster.anim")) {
            in.readInt(); // magic
            int count = in.readInt();
            for (int a = 0; a < count; a++) {
                String name = in.readUTF();
                float length = in.readFloat();
                boolean loop = in.readBoolean();
                int samples = in.readInt();
                int boneCount = in.readInt();
                float[][] tracks = new float[bones.size()][];
                for (int b = 0; b < boneCount; b++) {
                    String bone = in.readUTF();
                    float[] track = new float[samples * 6];
                    for (int k = 0; k < track.length; k++) {
                        track[k] = in.readFloat();
                    }
                    Integer idx = boneIndex.get(bone);
                    if (idx != null) tracks[idx] = track;
                }
                anims.put(name, new Anim(length, loop, samples, tracks));
            }
        }
    }
}

package org.orecruncher.dsurround.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum BiomeTrait {
    // Generic case of WTF
    UNKNOWN("UNKNOWN"),
    // Special internal traits for synthetic biomes
    SYNTHETIC("SYNTHETIC"),
    INSIDE("inside"),
    VILLAGE("VILLAGE"),
    PLAYER("PLAYER"),
    SPACE("SPACE"),
    CLOUDS("CLOUDS"),
    UNDER_RIVER("UNDER_RIVER"),
    UNDER_WATER("UNDER_WATER"),
    UNDER_OCEAN("UNDER_OCEAN"),
    // Biome categories as traits
    NONE("none"),
    TAIGA("taiga"),
    EXTREME_HILLS("extreme_hills"),
    JUNGLE("jungle"),
    MESA("mesa"),
    PLAINS("plains"),
    SAVANNA("savanna"),
    ICY("icy"),
    THEEND("the_end"),
    BEACH("beach"),
    FOREST("forest"),
    OCEAN("ocean"),
    DESERT("desert"),
    RIVER("river"),
    SWAMP("swamp"),
    MUSHROOM("mushroom"),
    NETHER("nether"),
    UNDERGROUND("underground"),
    /* Extended categories */
    WATER("WATER"),
    WET("WET"),
    DRY("DRY"),
    HOT("HOT"),
    COLD("COLD"),
    TEMPERATE("TEMPERATE"),
    SPARSE("SPARSE"),
    DENSE("DENSE"),
    CONIFEROUS("CONIFEROUS"),
    DECIDUOUS("DECIDUOUS"),
    SPOOKY("SPOOKY"),
    DEAD("DEAD"),
    MAGICAL("MAGICAL"),
    PLATEAU("PLATEAU"),
    MOUNTAIN("MOUNTAIN"),
    HILLS("HILLS"),
    SANDY("SANDY"),
    SNOWY("SNOWY"),
    WASTELAND("WASTELAND"),
    VOID("VOID"),
    OVERWORLD("OVERWORLD"),
    DEEP("DEEP"),
    WINDSWEPT("WINDSWEPT"),
    FLORAL("FLORAL"),
    BADLANDS("BADLANDS"),
    CAVES("CAVES"),
    RARE("RARE"),
    LUSH("LUSH");

    private static final Map<String, BiomeTrait> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(BiomeTrait::getName, (category) -> category));
    public static final Codec<BiomeTrait> CODEC = Codec.STRING.comapFlatMap(DataResult.partialGet(BY_NAME::get, () -> "unknown biome trait"), d -> d.name);

    private static final Map<String, BiomeTrait> mapper = new HashMap<>();

    static {
        register(BiomeTrait.NONE); // stone_shore why?
        register(BiomeTrait.TAIGA);
        register(BiomeTrait.EXTREME_HILLS);
        register(BiomeTrait.JUNGLE);
        register(BiomeTrait.MESA);
        register(BiomeTrait.PLAINS);
        register(BiomeTrait.SAVANNA);
        register(BiomeTrait.ICY);
        register(BiomeTrait.THEEND);
        register(BiomeTrait.BEACH);
        register(BiomeTrait.FOREST);
        register(BiomeTrait.OCEAN);
        register(BiomeTrait.DESERT);
        register(BiomeTrait.RIVER);
        register(BiomeTrait.SWAMP);
        register(BiomeTrait.MUSHROOM);
        register(BiomeTrait.NETHER);
        register(BiomeTrait.UNDERGROUND);
        register(BiomeTrait.WATER);
        register(BiomeTrait.WET);
        register(BiomeTrait.DRY);
        register(BiomeTrait.HOT);
        register(BiomeTrait.COLD);
        register(BiomeTrait.TEMPERATE);
        register(BiomeTrait.SPARSE);
        register(BiomeTrait.DENSE);
        register(BiomeTrait.CONIFEROUS);
        register(BiomeTrait.DECIDUOUS);
        register(BiomeTrait.SPOOKY);
        register(BiomeTrait.DEAD);
        register(BiomeTrait.MAGICAL);
        register(BiomeTrait.PLATEAU);
        register(BiomeTrait.MOUNTAIN);
        register(BiomeTrait.HILLS);
        register(BiomeTrait.SANDY);
        register(BiomeTrait.SNOWY);
        register(BiomeTrait.WASTELAND);
        register(BiomeTrait.VOID);
        register(BiomeTrait.OVERWORLD);
        register(BiomeTrait.DEEP);
        register(BiomeTrait.WINDSWEPT);
        register(BiomeTrait.FLORAL);
        register(BiomeTrait.BADLANDS);
        register(BiomeTrait.CAVES);
    }

    private final String name;

    BiomeTrait(String name) {
        this.name = name.toUpperCase();
    }

    public static BiomeTrait of(String name) {
        var result = mapper.get(name.toUpperCase());
        return result == null ? UNKNOWN : result;
    }

    private static void register(BiomeTrait trait) {
        mapper.put(trait.name, trait);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
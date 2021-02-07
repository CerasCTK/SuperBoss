package net.ceras.superboss.api;

public class CerasAPI {
    private static AbilityListener abilityListener;

    public static AbilityListener getAbilityListener() {
        return abilityListener;
    }

    public static void registerListener(AbilityListener _abilityListener) {
        abilityListener = _abilityListener;
    }
}

package com.example.xixi.binfashizhan.chapter03;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : xi-xi
 */
class Secrets {
    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }
}


class Secret {
}

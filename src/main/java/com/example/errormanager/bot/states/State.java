package com.example.errormanager.bot.states;

import com.example.errormanager.bot.enums.DeveloperState;
import com.example.errormanager.bot.enums.HomeMenuState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Muhammadkomil Murodillayev, ср 11:27. 8/10/22
 */
public class State {
    private static final Map<String, DeveloperState> developerStateMap = new HashMap<>();

    private static final Map<String, HomeMenuState> homeMenuStateMap = new HashMap<>();

    public static void setDeveloperState(String chatId, DeveloperState state) {
        developerStateMap.put(chatId, state);
    }

    public static DeveloperState getDeveloperState(String chatId) {
        if (Objects.isNull(developerStateMap.get(chatId))) {
            State.setDeveloperState(chatId, DeveloperState.UNAUTHENTICATED);
        }
        return developerStateMap.get(chatId);
    }

    public static void setHomeMenuState(String chatId, HomeMenuState state) {
        homeMenuStateMap.put(chatId, state);
    }

    public static HomeMenuState getHomeMenuState(String chatId) {

        if (Objects.isNull(homeMenuStateMap.get(chatId))) {
            State.setHomeMenuState(chatId, HomeMenuState.MAIN_MENU);
        }
        return homeMenuStateMap.get(chatId);
    }



}

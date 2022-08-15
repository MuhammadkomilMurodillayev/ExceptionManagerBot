package com.example.errormanager.bot.states;

import com.example.errormanager.bot.enums.DeveloperState;
import com.example.errormanager.bot.enums.HomeMenuState;
import com.example.errormanager.bot.enums.UserServiceState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Muhammadkomil Murodillayev, ср 11:27. 8/10/22
 */
public class State {
    private static final Map<String, DeveloperState> developerStateMap = new HashMap<>();

    private static final Map<String, HomeMenuState> homeMenuStateMap = new HashMap<>();


    private static final Map<String, UserServiceState> userServiceStateMap = new HashMap<>();

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
        System.out.println(homeMenuStateMap.get(chatId));
        return homeMenuStateMap.get(chatId);
    }


    public static void setUserServiceState(String chatId, UserServiceState state) {
        userServiceStateMap.put(chatId, state);
    }

    public static UserServiceState getUserServiceState(String chatId) {

        if (Objects.isNull(userServiceStateMap.get(chatId))) {
            State.setUserServiceState(chatId, UserServiceState.FULL_NAME);
        }
        return userServiceStateMap.get(chatId);
    }



}

/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

import java.util.HashMap;
import java.util.ArrayList;

class ShootClientProfile {

    // HashMap<String, ArrayList<String>> loadState(String statePath) {
    //     //HashMap<String, ArrayList<String>> state = load(statePath);
    //     HashMap<String, ArrayList<String>> state = new HashMap<String, ArrayList<String>>();
    //     return state;
    // }

    // HashMap<String, ArrayList<String>> updateState(HashMap<String, ArrayList<String>> state, String stateArr,  int stateArrIdx, String stateArrValUpdate) {
    //     ArrayList<String> stateArrUpdate = state.get(stateArr);
    //     stateArrUpdate.set(stateArrIdx, stateArrValUpdate);
    //     state.put(stateArr, stateArrUpdate);
    //     return state;
    // }

    static HashMap<String, ArrayList<String>> initState() {
        HashMap<String, ArrayList<String>> state = new HashMap<String, ArrayList<String>>();
        // ArrayList<String> client = new ArrayList<String>();
        // ArrayList<String> session = new ArrayList<String>();
        // ArrayList<String> account = new ArrayList<String>();
        // ArrayList<String> settings = new ArrayList<String>();
        /* 
            HashMap<String, ArrayList<String>> state = {
            "client": [username, user_id, registered],
            "session": [socket_info, client_computer, session_status],
            "account": [rooms, archive, resources],
            "settings": [save_mode, encryption, autoload] }
        */

        // client.add(getUsername());
        // client.add(getUserId());
        // client.add("false");
        // state.put("client", client);

        // session.add(getClientComputer());
        // session.add(getSocketInfo());
        // session.add(getStatus());
        // state.put("session", session);

        // account.add("open");
        // account.add("userid_archive.json");
        // account.add("/resources");
        // state.put("account", account);

        // settings.add("all");
        // settings.add("yes");
        // settings.add("yes");
        // state.put("settings", settings);

        return state;
    }
    
}

/*
 *  Shoot Messenger 0.0.1
 *  Luke McConnell
*/

package com.lukemcconnell.shoot.messenger;

/**
 * UserProfile stores user information.
 */
class UserProfile {

    private String[] userInfo;
    private boolean loggedIn;

    /**
     * UserProfile constructor.
     * 
     */
    UserProfile () {loggedIn = false;}

    /**
     * Sets user infomation in string array userInfo.
     * @param userInfo
     */
    void setUserInfo(String[] userInfo) {this.userInfo = userInfo;}

    /**
     * Returns String array of userInfo.
     * 
     * @return
     */
    String[] getUserInfo() {return userInfo;}
    
    /**
     * Returns boolean of user login status.
     * @return
     */
    boolean isLoggedIn() {return loggedIn;}

    /**
     * Sets UserProfile login status.
     * 
     * @param loggedIn
     */
    void setLoggedIn(boolean loggedIn) {this.loggedIn = loggedIn;}
    
}

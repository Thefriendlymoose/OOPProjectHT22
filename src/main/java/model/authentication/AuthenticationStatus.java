package model.authentication;

/**
 * Enum used to show status when user tries to log in/out
 *
 * @author David al Amiri
 */
public enum AuthenticationStatus {
    INCORRECT_PASSWORD, USER_NOT_FOUND, SUCCESS, ALREADY_USER_LOGGED_IN, NO_CURRENT_USER
}

package me.marcelberger.weatherapp.assistant.enumeration.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatRoleEnum {
    ASSISTANT(ChatRoleEnum.ASSISTANT_VALUE),
    USER(ChatRoleEnum.USER_VALUE),
    FUNCTION(ChatRoleEnum.FUNCTION_VALUE),
    SYSTEM(ChatRoleEnum.SYSTEM_VALUE);

    public static final String ASSISTANT_VALUE = "ASSISTANT";
    public static final String USER_VALUE = "USER";
    public static final String FUNCTION_VALUE = "FUNCTION";
    public static final String SYSTEM_VALUE = "SYSTEM";

    private final String value;
}

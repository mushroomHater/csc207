package com.example.uoftlife.util;

import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;

import java.lang.reflect.Field;

public class ResourceReader {
    static String getRepresentingText(String variableName) throws Exception {
        if (DataFacade.getContext() != null) {
            Field idField = R.string.class.getDeclaredField(variableName);
            return DataFacade.getContext().getString(idField.getInt(idField));
        } else {
            throw new NullPointerException();
        }
    }


}

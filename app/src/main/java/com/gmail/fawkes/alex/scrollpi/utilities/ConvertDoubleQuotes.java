package com.gmail.fawkes.alex.scrollpi.utilities;

import com.gmail.fawkes.alex.scrollpi.functional.Function1;

class ConvertDoubleQuotes implements Function1<String, String> {
    @Override
    public String execute(final String string) {
        return string.replace("\"", "'");
    }
}

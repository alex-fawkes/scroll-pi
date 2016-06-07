package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.functional.Function1;

class FailedCaseToJson implements Function1<String, CaseFailedException> {
    @Override
    public String execute(CaseFailedException exception) {
        return exception.toJson();
    }
}

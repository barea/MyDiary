package com.example.mydiary.data.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class diary {

    public abstract int id();
    public abstract String timestamp();
    public abstract String title();
    public abstract String description();

    public static diary Create(int id, String timestamp, String title, String description)
    {
        return new AutoValue_diary(id, timestamp, title, description);
    }


}

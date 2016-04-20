package com.craftexercise.interfac;

public class DemoResponse
{
    String message;

    public DemoResponse(String message)
    {
        super();
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}

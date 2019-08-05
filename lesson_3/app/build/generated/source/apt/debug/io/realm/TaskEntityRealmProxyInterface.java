package io.realm;


public interface TaskEntityRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public boolean realmGet$done();
    public void realmSet$done(boolean value);
    public long realmGet$timestamp();
    public void realmSet$timestamp(long value);
}

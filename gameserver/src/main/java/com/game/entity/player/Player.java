package com.game.entity.player;


import com.engine.task.ConsecutiveTask;
import com.engine.task.tasks.PlayerTickTask;
import com.engine.task.tasks.PlayerUpdateTask;
import com.game.entity.Entity;
import com.net.ActionSender;
import io.netty.channel.Channel;


public class Player extends Entity {

    private ActionSender actionSender = new ActionSender(this);

    private ConsecutiveTask updateTask = new ConsecutiveTask(new PlayerUpdateTask(this));
    private PlayerTickTask tickTask = new PlayerTickTask(this);

    private Channel channel;
    private String username;
    private String password;

    public Player(Channel channel, String username, String password) {
        this.channel = channel;
        this.username = username;
        this.password = password;
    }

    public PlayerTickTask getTickTask() {
        return tickTask;
    }

    public ConsecutiveTask getUpdateTask() {
        return updateTask;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ActionSender getActionSender() {
        return actionSender;
    }

}

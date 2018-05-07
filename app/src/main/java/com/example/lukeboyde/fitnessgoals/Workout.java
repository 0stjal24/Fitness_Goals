package com.example.lukeboyde.fitnessgoals;

/**
 * Created by lukeboyde on 30/03/2018.
 */

public class Workout {
    private String name;
    private String description;

    //Workouts is an array of Workouts
    public static final Workout[] workouts = {
            new Workout("6-round Bodyweight WOD",
                    "Do 6 rounds of:\n10 Pushups\n15 V-Ups\n20 Jump squats"),
            new Workout("16-minute AMRAP",
                    "In 16 minutes, do as many rounds as possible(AMRAP)of:\n10 Burpees\n10 Pullups\n20 Lunges\n20 Situps"),
            new Workout("20-minute EMOM",
                    "Do this pattern 5 times for 20 total minutes.\nMinute 1:15 Pushups\nMinute 2:10 Pullups\nMinute3:15 Situps\nMinute 4:20 Lunges"),
            new Workout("Ten to one",
                    "This workout uses an ascending/descending rep scheme,\nBurpees: 10-9-8-7-6-5-4-3-2-1\nPullups: 1-2-3-4-5-6-7-8-9-10"),
            new Workout("10 rounds of 20",
                    "Do 10 rounds of the following:\n20 Mountain Climbers\n20 Situps\n20 Air squats\n20 Lunges"),
            new Workout("Murph",
                    "1-mile Run\n100 Pullups\n200 Pushups\n300 Air squats\n 1-mile Run"),
            new Workout("7-minute AMRAP",
                    "In seven minutes, do as many rounds as possible of the following:\n10 Lunges\n10 Mountain Climbers\n10 Pushups"),
            new Workout("16-minute Upper-Body AMRAP",
                    "In 16 minutes do as many rounds as possible of:\n8 Burpees\n8 Push ups\n16 Pullups\n16 Hanging Knee tucks or Situps"),
            new Workout("Full-throttle AMRAP",
                    "In 20 minutes, do as many rounds as possible of:\n21 Jumping Air Squats\n15 Pushups\n9 Burpee Pullups"),
            new Workout("High-rep Bodyweight Classic",
                    "Do eight rounds of the following:\n10 Pushups\n10 Air squats\n10 Burpees\n10 Air squats")


    };
    //Each Workout has a name and description.
    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //getters for private variables
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    //String representation of a wokrout is its name
    public String toString() {
        return this.name;
    }
}

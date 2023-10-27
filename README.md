# ChristmasFlair
# 10/26/2023 

Command pattern has been solved. I was returning "this.led" as null on 10/14/2023 because the Receiver was being utilized improperly. Now the Client needs to be written in a way that allows for choice. To elaborate: right now the client executes ALL invoked commands one by one. We will need something like "press 1 for rainbow(), 2 for strobe(), etc etc)."

EDIT: While updating the code on a CR system, I accidentally pushed & committed some stuff while under the wrong username. I thought I signed in on my account but I guess I did something wrong. If you see another username in the commit message history, that's why. Just felt it important to clarify that.


# 10/14/2023

For this sprint, I did two things: 


1.) Made an LedAnimations class (in src directory) which contains a rainbow method and a strobe method. 


2.) Attempted to work with the Command design pattern to make a way for the user to access different animations. This can be found in the directory titled "Command_Idea".


My command design pattern doesn't seem to be properly written yet. I get the following error:

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Command_Idea.LedReceiver.rainbowCommand()" because "this.led" is null
	at Command_Idea.RainbowCommand.execute(RainbowCommand.java:14)
	at Command_Idea.LedInvoker.execute(LedInvoker.java:10)
	at Command_Idea.LedClient.main(LedClient.java:12)
```
I think this is because I am putting the code for the animations in the wrong place. 

Below is the UML diagram for what I have in mind. It's very similar to the FileSystemReceiver example we went over in class.

```
classDiagram
    RainbowCommand ..|> LedCommand :implements
    RainbowCommand --> LedReceiver
    StrobeCommand ..|> LedCommand :implements
    StrobeCommand --> LedReceiver
    LedInvoker --> LedCommand
    LedClient --> LedReceiver
    LedClient --> RainbowCommand
    LedClient --> StrobeCommand
    LedWs281xReceiver ..|> LedReceiver :implements
    LedSignReceiver ..|> LedReceiver :implements

    class LedCommand{
        <<interface>>
        +execute() : void
    }

    class RainbowCommand{
        -LedReceiver led
        +RainbowCommand(LedReceiver ledReceiver)
        +execute(): void "led.rainbowCommand()"
    }
    class StrobeCommand{
        -LedReceiver receiver
        +StrobeCommand(LedReceiver receiver)
        +execute(): void "led.strobeCommand()"
    }

    class LedReceiver {
        <<interface>>
        +rainbowCommand() : void
        +strobeCommand() : void
    }
    class LedWs281xReceiver {
        +rainbowCommand() : void
        +strobeCommand() : void
    }
    class LedSignReceiver {
        +rainbowCommand() : void
        +strobeCommand() : void
    }

    class LedInvoker {
        -LedCommand command
        +LedInvoker(LedCommand c)
        +execute()
    }

    class LedClient{
        LedReceiver ledReceiver
        RainbowCommand rainbowCommand
        StrobeCommand strobeCommand
        LedInvoker ledInvoker
        +execute()
    } 
```
# 9/23/2023

This is where my mermaid.live class diagram currently stands. The goal of this diagram is to make a set of animations that the user can choose by utilizing the decorator design pattern. Current animation ideas include strobe, scroll, and rainbow.



```classDiagram

LedStatic ..|> Led : implements
LedAnim ..|> Led : implements
LedScroll --|> LedAnim : extends
LedStrobe --|> LedAnim : extends
LedRainbow --|> LedAnim : extends
LedAnim *-- Led : extends

class Led{
<<interface>>
+render()
}

class LedStatic{
+LedStatic()
+render()
+delay()
}

class LedAnim{
led wrappedLed
+ledAnim(Led)
+render()
+delay()
}

class LedScroll{
+ledScroll(Led)
+render()
+delay()
}

class LedStrobe{
+ledStrobe(Led)
+render()
+delay()
}

class LedRainbow{
Object newState
+ledRainbow(Led)
+render()
+delay()
}
```


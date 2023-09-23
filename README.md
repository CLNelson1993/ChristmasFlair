# ChristmasFlair

updated 9/23/2023

This is where my mermaid.live class diagram currently stands. The goal of this diagram is to make a set of animations that the user can choose by utilizing the decorator design pattern. Current animation ideas include strobe, scroll, and rainbow.



classDiagram

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
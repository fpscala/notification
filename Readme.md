Notification alert for ScalaJs & React
=========================
Usage
-------------

### Contents

* [Setup](#setup)
* [Using notification](#Using notification)

### Setup

1. Add [Scala.js](http://www.scala-js.org) to your project.
2. Add [scalajs-react](https://github.com/japgolly/scalajs-react/) to your project.
3. Add [ScalaCss](https://japgolly.github.io/scalacss/book/) to your project.
4. Add Notification to your build sbt.

```scala
 libraryDependencies += "uz.scala" %%% "notification" % "2.0"
```

### Using notification

You need to add `Notification.render()` to the render section, and add `NotificationStyle.addToDocument()` before rendering

### Example

```scala
val MyComponent =
      ScalaComponent.builder[Unit]
       .render { _ =>
         <.div(
           Notification.render(),
           ???
         )
       }
       .build

    NotificationStyle.addToDocument()
    MyComponent().renderIntoDOM(document.getElementById("need-to-render-div-id"))
```

Also, you can also set the notification retention time using the Notification constructor. This figure is usually 6 seconds

```scala
...
Notification.render(10) // this value in seconds
...
```

This library has 4 notification type for using, and these returned [ScalaJs & React - Callbacks](https://github.com/japgolly/scalajs-react/blob/master/doc/CALLBACK.md). For example:
```scala
Notification.info("This is information box!")

Notification.warning("This is warning box!")

Notification.error("This is error box!")

Notification.success("This is success box!")
```
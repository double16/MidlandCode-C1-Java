# Debugger

Debugging is using a tool like IntelliJ or Eclipse to watch and control code as it is executing. It is a powerful tool and will help you fix problems in your code. Debugging is a step beyond logging. It requires running your tool on the same machine as your Java application, or being able to connect to it through the network.

## Starting with the Debugger

It's easiest to use the IDE `Debug` function to start the debugger. It will start your application and "attach" to it in order to debug.

You can also run your application in debug mode, then attach the IDE to it. This takes special arguments to the `java` command, we'll rely on the IDE for now.

## Breakpoints

A break point is a line, or event, in your program at which you want to pause the program and inspect what's going on. It's most common to click to the left of the source line and the IDE will add a breakpoint. You could also right-click the source line and choose the menu option to add a breakpoint.

You can also set a break point when an exception occurs. This is very helpful when debugging a stack trace. However, some generic exceptions such as `NullPointerException` happen often though you don't see them.

## Stepping

Once the debugger has stopped at a breakpoint, you can step through the code. There are usually three types of step operations:

* Step Into will go to the next line and if it is a method or function call, go into the method.
* Step Over will go to the next line and if it is a method or function call, will let the function execute without stepping into it.
* Step Out or Up will finish the current method and return to the caller.

Which one you use depends on the code and what issues you are trying to find. For example, you rarely need to step into classes provided by the JDK, i.e. things in packages like `java.lang` and `java.util`.

## Inspecting Variables

The usual thing you'd like to do when debugging is to inspect data. The debugger will let you see the fields and object data when stopped at a break point. The available data is based on the line you're in. If you click higher up in the call tree you can see different things.

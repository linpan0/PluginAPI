package me.backword.pluginapi.commands.exception

class CommandException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
}
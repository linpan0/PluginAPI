package me.backword.pluginapi.commands.exception

class InvalidSyntaxException : RuntimeException {
  constructor() : super()
  constructor(message: String) : super(message)
}
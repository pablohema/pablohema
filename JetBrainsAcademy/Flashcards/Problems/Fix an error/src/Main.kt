class Player(val id: Int, val name: String) {
    companion object {
        private const val role = "playable character"
    }
    fun getInfo(): String = "$id, $name, $role"
}

fun getPlayerInfo(player: Player) = player.getInfo()

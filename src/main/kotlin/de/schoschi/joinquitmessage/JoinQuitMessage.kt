package de.schoschi.joinquitmessage

import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.*

class JoinQuitMessage : KSpigot(), Listener {

    val permissionSection = config.getConfigurationSection("permission")!!
    val permittedJoin = permissionSection.getBoolean("only_permitted_join") ?: true
    val permission = permissionSection.getString("bypass_permission") ?: "joinquit.permitted.bypass"

    override fun load() {
        saveDefaultConfig()
    }

    override fun startup() {
        if (permittedJoin) {
            onlinePlayers.forEach { player ->
                if (!player.hasPermission(permission)) {
                    player.kick(config.getString("not_permitted_join_message")!!.tranlateCode('&').toComponent())
                } else {
                    player.sendMessage("§e§lWarnung!§rDer Wartungsmodus ist aktiv.")
                }
            }
        }
        pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onLogin(event: PlayerLoginEvent) {
        if (!permittedJoin)
            return
        val player = event.player
        if (player.hasPermission(permission)) {
            player.sendMessage("§e§lWarnung!§r Momentan ist noch eine Whitelist aktiv, um diese zu deaktivieren, editiere die \'config.yml\' im Ordner \'plugins\\JoinQuitMessage\'")
        } else {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, config.getString("not_permitted_join_message")!!.tranlateCode('&').toComponent())
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage(config.getString("join_message")!!.tranlateCode('&').toComponent())
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage(config.getString("join_message")!!.tranlateCode('&').toComponent())
    }

    fun String.tranlateCode(c: Char) = ChatColor.translateAlternateColorCodes(c, this)
    fun String.toComponent() = Component.text(this)

}
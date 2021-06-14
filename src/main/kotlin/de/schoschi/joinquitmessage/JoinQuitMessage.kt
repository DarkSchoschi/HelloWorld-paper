package de.schoschi.joinquitmessage

import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuitMessage : KSpigot(), Listener {

    override fun startup() {
        pluginManager.registerEvents(this, this)
    }

    override fun shutdown() {

    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage(Component.text("[§c-§r] ${event.player.name}"))
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage(Component.text("[§2+§r] ${event.player.name}"))
    }

}
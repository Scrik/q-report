package ru.redenergy.report.client.ui

import com.rabbit.gui.background.DefaultBackground
import com.rabbit.gui.component.control.Button
import com.rabbit.gui.component.display.TextLabel
import com.rabbit.gui.render.TextAlignment
import com.rabbit.gui.show.Show
import net.minecraft.client.resources.I18n
import ru.redenergy.report.client.QReportClient
import ru.redenergy.report.client.ui.admin.AdminCenter
import ru.redenergy.report.client.ui.admin.BlockedListShow
import ru.redenergy.report.client.ui.admin.ManageTicketsShow
import ru.redenergy.report.common.network.NetworkHandler
import ru.redenergy.report.common.network.packet.requests.RequestSyncPacket

class SupportShow: Show() {

    init {
        background = DefaultBackground()
        NetworkHandler.sendToServer(RequestSyncPacket())
    }

    override fun setup() {
        super.setup()
        registerComponent(TextLabel(this.width / 3, this.height / 3 / 2, this.width / 3, I18n.format("show.support.title"))
                        .setTextAlignment(TextAlignment.CENTER))
        registerComponent(Button(this.width / 3, this.height / 3, this.width / 3, 20, I18n.format("show.support.new"))
                        .setClickListener { this.getStage().display(ReportShow()) })
        registerComponent(Button(this.width / 3, this.height / 3 + 30, this.width / 3, 20, I18n.format("show.support.previous"))
                        .setClickListener { this.getStage().display(TicketsListShow()) })
        if(QReportClient.adminAccess) {
            registerComponent(Button(this.width / 3, this.height / 3 + 60, this.width / 3, 20, I18n.format("show.admin.title"))
                    .setClickListener { this.getStage().display(AdminCenter()) })
            registerComponent(Button(this.width / 3, this.height / 3 + 90, this.width / 3, 20, "Blocked Players")
                    .setClickListener { this.getStage().display(BlockedListShow()) })
        }
        registerComponent(Button(this.width / 3, this.height / 3 * 2 + 10, this.width / 3, 20, I18n.format("show.support.close"))
                        .setClickListener { this.getStage().displayPrevious() })
    }
}
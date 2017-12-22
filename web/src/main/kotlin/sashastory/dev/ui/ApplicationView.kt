package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.server.Page
import com.vaadin.shared.Position
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.model.Application
import sashastory.dev.model.Form
import sashastory.dev.service.*
import java.util.*

/**
 * @author Александр
 * @date 19.12.2017
 */
@AutoView
class ApplicationView : VerticalLayout(), View {

    private lateinit var schoolSelect: NativeSelect<String>
    private lateinit var formSelect: NativeSelect<String>
    private lateinit var  sendButton: Button
    private lateinit var selectedSchool: String
    private lateinit var selectedForm: String
    private var schoolMap: Map<String?,Long?>
    private lateinit var formMap: Map<String?, Long?>

    init {
        isSpacing = false
        schoolMap = SchoolSearchService.schoolProvider.getSchoolsWithFreeSpots().map { it.schoolName to it.id }.toMap()
        verticalLayout {
            label("Прием заявок на поступление") {
                alignment = Alignment.TOP_LEFT
                addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
            }
            panel("Критерии") {
                w = 40.perc
                icon = VaadinIcons.CALENDAR
                addStyleNames("color2")
                verticalLayout {
                    setSizeFull()
                    schoolSelect = nativeSelect("Выберите школу со свободными местами") {
                        setSizeFull()
                        setItems(schoolMap.keys)
                        addSelectionListener {
                            selectedSchool = value
                            update()
                        }
                    }
                    formSelect = nativeSelect("Теперь выберите доступный класс") {
                        setSizeFull()
                        addSelectionListener {
                            selectedForm = value
                            sendButton.isVisible = true
                        }
                    }
                    sendButton = button("Отправить") {
                        setSizeFull()
                        addStyleNames(ValoTheme.BUTTON_FRIENDLY)
                        onLeftClick { apply() }
                    }
                }
                update()
            }
        }
    }

    private fun update() {

        formSelect.isVisible = false
        sendButton.isVisible = false

        if (schoolSelect.value != null) {

            val forms: List<Form>
                    = FormSearchService.formProvider.getFormsWithSpotsForSchoolId(schoolMap[schoolSelect.value]!!)
            formMap = forms.map { it.formNumber to it.id }.toMap()

            formSelect.isVisible = true
            formSelect.setItems(formMap.keys)

            sendButton.isVisible = true

        }
    }


    private fun apply() {
        if (selectedSchool.isNotEmpty() && selectedForm.isNotEmpty()) {
            val application = Application(
                    appUserId = SecurityService.currentUser?.id,
                    schoolId = schoolMap[selectedSchool],
                    formId = formMap[selectedForm],
                    applicationDate = Date())
            ApplicationService.apply(application)
            Notification("Поздравляем!", "Ваша заявка была успешно сформирована",
                    Notification.Type.HUMANIZED_MESSAGE).apply {
                styleName = "${ValoTheme.NOTIFICATION_CLOSABLE} ${ValoTheme.NOTIFICATION_SUCCESS}"
                position = Position.TOP_CENTER
                show(Page.getCurrent())
            }
        }

    }
}
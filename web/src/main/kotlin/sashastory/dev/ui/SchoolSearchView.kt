package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.shared.ui.ValueChangeMode
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.model.School
import sashastory.dev.service.DataService
import java.time.LocalDate

/**
 * @author Александр
 * @date 17.12.2017
 */

@AutoView
class SchoolSearchView : VerticalLayout(), View {

    private var grid: Grid<School>? = null
    private var schoolNameFilter: TextField
    private var principalFilter: TextField
    private var addressFilter: TextField
    private var phoneFilter: TextField
    private var dateFilter: DateField
    private var options: Map<String, AbstractComponent>
    private var filterSelector: RadioButtonGroup<String>

    init {
        label("Выберите критерий для поиска школы") {
            alignment = Alignment.TOP_LEFT
            addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
        }
        schoolNameFilter = textField {
            alignment = Alignment.BOTTOM_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите номер школы"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listSchoolsByFilter(e.value, this) }
        }
        principalFilter = textField {
            alignment = Alignment.BOTTOM_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите ФИО директора"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listSchoolsByFilter(e.value, this) }
        }
        addressFilter = textField {
            alignment = Alignment.BOTTOM_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите адрес школы"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listSchoolsByFilter(e.value, this) }
        }
        phoneFilter = textField {
            alignment = Alignment.BOTTOM_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите номер телефона школы"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listSchoolsByFilter(e.value, this) }
        }
        dateFilter = dateField {
            alignment = Alignment.BOTTOM_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.DATEFIELD_BORDERLESS
            placeholder = "Выберите дату открытия школы"
            addValueChangeListener { e -> listSchoolByDate(e.value) }
        }
        options = mapOf(
                "По номеру" to schoolNameFilter,
                "По директору" to principalFilter,
                "По адресу" to addressFilter,
                "По телефону" to phoneFilter,
                "По дате основания" to dateFilter)
        filterSelector = radioButtonGroup {
            alignment = Alignment.TOP_LEFT
            styleName = ValoTheme.OPTIONGROUP_HORIZONTAL
            w = fillParent
            setItems(options.keys)
            addSelectionListener { update() }
        }
        update()

    }

    private fun update() {

        walk().filterIsInstance<TextField>().forEach {
            it.clear()
            it.isVisible = false
        }
        dateFilter.apply {
            clear()
            isVisible = false
        }

        if (filterSelector.value != null)
            options.get(filterSelector.value)?.isVisible = true

        if (grid != null)
            removeComponent(grid)

        grid = grid(School::class) {
            alignment = Alignment.MIDDLE_LEFT
            h = 300.px
            w = 1000.px
            showColumns(School::schoolName, School::principal, School::schoolAddress,
                    School::phone, School::foundationDate)
            columns[0].caption = "Имя школы"
            columns[1].caption = "Директор"
            columns[2].caption = "Адрес школы"
            columns[3].caption = "Номер телефона"
            columns[4].caption = "Дата создания"
        }
        listAllSchools()
    }


    private fun listAllSchools() {
        grid?.setItems(DataService.schoolDao.getAllSchools())
    }

    private fun listSchoolsByFilter(filterText: String, filter: AbstractComponent) {
        if (filterText.isEmpty()) {
            listAllSchools()
            return
        }
        when (filter) {
            schoolNameFilter -> grid?.setItems(DataService.schoolDao.getSchoolsByName(filterText))
            principalFilter -> grid?.setItems(DataService.schoolDao.getSchoolsByPrincipal(filterText))
            addressFilter -> grid?.setItems(DataService.schoolDao.getSchoolsByAddress(filterText))
            phoneFilter -> grid?.setItems(DataService.schoolDao.getSchoolsByPhone(filterText))
        }
    }

    private fun listSchoolByDate(filterDate: LocalDate) {
        grid?.setItems(DataService.schoolDao.getSchoolsByFoundationDate(filterDate))
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {

    }
}
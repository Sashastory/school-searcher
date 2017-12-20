package sashastory.dev.ui

import com.github.vok.karibudsl.*
import com.vaadin.icons.VaadinIcons
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.shared.ui.ValueChangeMode
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import sashastory.dev.dao.FormDao
import sashastory.dev.model.Form

/**
 * @author Александр
 * @date 19.12.2017
 */
@AutoView
class FormSearchView : VerticalLayout(), View {

    private var grid: Grid<Form>? = null
    private var formNumberFilter: TextField
    private var teacherNameFilter: TextField
    private var studentAmountFilter: TextField
    private var options: Map<String, TextField>
    private var filterSelector: RadioButtonGroup<String>

    init {
        label("Выберите критерий для поиска классов") {
            alignment = Alignment.TOP_LEFT
            addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_COLORED)
        }
        formNumberFilter = textField {
            alignment = Alignment.TOP_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите номер класса"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listFormsByFilter(e.value, this) }
        }
        teacherNameFilter = textField {
            alignment = Alignment.TOP_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите ФИО классного руководителя"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listFormsByFilter(e.value, this) }
        }
        studentAmountFilter = textField {
            alignment = Alignment.TOP_LEFT
            isVisible = false
            w = 50.perc
            styleName = ValoTheme.TEXTFIELD_INLINE_ICON
            icon = VaadinIcons.SEARCH
            caption = "Введите количество учеников"
            valueChangeMode = ValueChangeMode.LAZY
            addValueChangeListener { e -> listFormsByFilter(e.value, this) }
        }
        options = mapOf("По номеру класса" to formNumberFilter,
                "По имени классного руководителя" to teacherNameFilter,
                "По количеству учеников" to studentAmountFilter)
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
        if (filterSelector.value != null)
            options.get(filterSelector.value)?.isVisible = true

        if (grid != null)
            removeComponent(grid)

        grid = grid(Form::class) {
            alignment = Alignment.TOP_LEFT
            w = 600.px
            showColumns(Form::formNumber, Form::teacherName, Form::studentAmount)
            columns[0].caption = "Номер класса"
            columns[1].caption = "ФИО классного руководителя"
            columns[2].caption = "Количество учеников"
        }
        listAllForms()
    }

    private fun listAllForms() {
        grid?.setItems(FormDao.getAllForms())
    }

    private fun listFormsByFilter(filterText: String, filter: TextField) {
        if (filterText.isEmpty()) {
            listAllForms()
            return
        }
        when (filter) {
            formNumberFilter -> grid?.setItems(FormDao.getFormsByNumber(filterText))
            teacherNameFilter -> grid?.setItems(FormDao.getFormsByTeacherName(filterText))
            studentAmountFilter -> grid?.setItems(FormDao.getFormsByStudentAmount(filterText.toInt()))
        }
    }

    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}
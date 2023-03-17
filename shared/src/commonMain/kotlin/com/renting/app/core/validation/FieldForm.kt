package com.renting.app.core.validation

data class FieldForm(
    private val idToField: Map<TextField.Id, TextField>,
) {

    constructor(fields: List<TextField>) : this(fields.associateBy(TextField::id))

    fun getValue(kind: TextField.Kind): String {
        return idToField.getValue(TextField.Id(kind)).value
    }

    fun updateField(id: TextField.Id, update: TextField.() -> TextField): FieldForm {
        return FieldForm(
            idToField = idToField.toMutableMap().apply {
                val prev = getValue(id)
                this[id] = prev.update()
            },
        )
    }
}

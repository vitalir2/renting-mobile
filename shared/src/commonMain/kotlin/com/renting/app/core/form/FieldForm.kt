package com.renting.app.core.form

data class FieldForm(
    private val idToField: Map<TextField.Id, TextField>,
) : Iterable<TextField> {

    constructor(fields: List<TextField>) : this(fields.associateBy(TextField::id))

    override operator fun iterator(): Iterator<TextField> {
        return idToField.values.iterator()
    }

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

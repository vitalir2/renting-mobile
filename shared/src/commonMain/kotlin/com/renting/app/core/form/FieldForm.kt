package com.renting.app.core.form

class FieldForm private constructor(
    private val idToField: Map<TextField.Id, TextField>,
    val fields: List<TextField>,
) : Iterable<TextField> {

    constructor(idToField: Map<TextField.Id, TextField>) : this(
        idToField = idToField,
        fields = idToField.values.toList(),
    )

    constructor(fields: List<TextField>) : this(
        idToField = fields.associateBy(TextField::id),
        fields = fields,
    )

    override operator fun iterator(): Iterator<TextField> {
        return idToField.values.iterator()
    }

    val firstErrorField: TextField?
        get() {
            return idToField.values.firstOrNull { it.error != null }
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

    fun applyErrors(errors: Map<TextField.Id, String>): FieldForm {
        return errors.toList().fold(this) { form, (id, error) ->
            form.updateField(id) { copy(error = error) }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as FieldForm

        if (fields != other.fields) return false

        return true
    }

    override fun hashCode(): Int = fields.hashCode()

    override fun toString(): String {
        return "FieldForm(fields=$fields)"
    }
}

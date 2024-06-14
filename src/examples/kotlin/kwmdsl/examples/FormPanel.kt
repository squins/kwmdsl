package kwmdsl.examples

import com.squins.kwmdsl.Wicket
import com.squins.kwmdsl.div
import com.squins.kwmdsl.form
import com.squins.kwmdsl.input
import com.squins.kwmdsl.label
import com.squins.kwmdsl.markup
import org.apache.wicket.MarkupContainer
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.IModel
import java.io.Serializable

class FormPanel(id: String, model: IModel<*>) : Panel(id, model), IMarkupResourceStreamProvider {
    private val fullNameModel = CompoundPropertyModel(FullName())
    
    private val fullNameForm by Wicket { Form(it, fullNameModel) }
    
    private val firstNameLabel by Wicket { Label(it, "First name") }
    
    private val firstName by Wicket { TextField<String?>(it) }
    
    private val lastNameLabel by Wicket { Label(it, "Last name") }
    
    private val lastName by Wicket { TextField<String?>(it) }

    override fun onInitialize() {
        super.onInitialize()

        myMarkup.addTo(this)
    }

    override fun getMarkupResourceStream(
        container: MarkupContainer?,
        containerClass: Class<*>?
    ) = myMarkup.stream

    companion object {
        private val myMarkup = markup {
            wicketPanel {
                form(FormPanel::fullNameForm) {
                    div {
                        div {
                            label(FormPanel::firstNameLabel)
                        }
                        div {
                            input(FormPanel::firstName)
                        }
                    }
                    div {
                        div {
                            label(FormPanel::lastNameLabel)
                        }
                        div {
                            input(FormPanel::lastName)
                        }
                    }
                }
            }
        }
    }
}

private class FullName : Serializable {
    var firstName: String? = null
    var lastName: String? = null
}

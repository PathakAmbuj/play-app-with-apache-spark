package controllers

import javax.inject.Inject
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, AnyContent, Controller}
import views.html.form

import scala.concurrent.Future

class FormController @Inject()(implicit webJarAssets: WebJarAssets,
                               val messagesApi: MessagesApi) extends Controller{

  def index: Action[AnyContent] = {
    Action.async {
      Future.successful(Ok(form.render()))
    }
  }
}

package cl.blackmind.app_domain.model

data class Task(
  private val id: Long,
  private val title: String,
  private val description: String
)
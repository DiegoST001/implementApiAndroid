import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanchez.diego.ejercicio01.TeacherResponse
import com.sanchez.diego.ejercicio01.databinding.ItemTeacherBinding

class TeacherAdapter(private val teachers: List<TeacherResponse>) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int {
        return teachers.size
    }

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: TeacherResponse) {

            binding.txtFullName.text = teacher.getFullName()
            binding.txtPhone.text = teacher.getPhoneNumber()
            binding.txtEmail.text = teacher.email

            Glide.with(itemView.context)
                .load(teacher.getProfileImage())
                .into(binding.imgTeacher)

            binding.root.setOnClickListener {
                val phoneNumber = teacher.getPhoneNumber()
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                itemView.context.startActivity(dialIntent)
            }

            binding.root.setOnLongClickListener {
                val email = teacher.email
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre clases")
                itemView.context.startActivity(Intent.createChooser(emailIntent, "Enviar correo"))
                true
            }
        }
    }
}

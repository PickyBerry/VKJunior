package com.pickyberry.vkjunior



import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pickyberry.vkjunior.databinding.ContactsItemBinding


//Адаптер для recyclerview контактов
class ContactsAdapter() :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactsItemBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = differ.currentList[position]
        val binding=holder.binding

        holder.itemView.apply {
            binding.photo.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    item.photo,
                    null
                )
            )
            binding.name.text=item.name

            //при нажатии на контакт переходим в звонок и отправляем в экран звонка данные о собеседнике
            binding.root.setOnClickListener{
                val bundle= Bundle()
                bundle.putString("name",item.name)
                bundle.putInt("photo",item.photo)
                it.findNavController().navigate(R.id.action_contactsFragment_to_callFragment,bundle)
            }
        }
    }



    override fun getItemCount() = differ.currentList.size


    //для примера заполним просто одинаковыми контактами
    init{
        val list = differ.currentList.toMutableList()
        repeat(15){
        list.add(Contact("Venera Phone Long Contact For Test and Test Again",R.drawable.user_01))
        }
        differ.submitList(list)
    }



    class ContactsViewHolder(
        val binding: ContactsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}
<template>

    <q-card dark bordered class="bg-grey-9 my-card">
      <q-card-section>
        <div class="text-h6"> {{title}} </div>
        <div class="text-subtitle2"> by: {{author.email}} </div>
      </q-card-section>

      <q-separator dark inset />

      <q-card-section>
        {{content}}
      <q-separator dark inset />
        {{ date }}

      <q-separator dark inset />
      
        <q-btn color="yellow" :click="updatePost(idPost)" icon-right="update" label="update" />
        <q-btn color="red" :click="delete(idPost)" icon-right="delete" label="delete" />

      </q-card-section>
    </q-card>

</template>

<script>
export default {
  name: 'PostList',
  props: {
    idPost:{
      type: Number
    },
    title:{
        type: String,
    },
    content:{
        type: String,
    },
    date:{
        type: String,
    },
    author:{
        type: Object,
    },
    async updatePost(id){
console.log('update')
    },
    async delete(id){
      
      console.log('fin')
//      const request = {
//        method:'GET',
//        url:'/deletePost',
//        params: {
//          'idPost': id
//        }
//      }
//      let axiosRequest = await this.$axios(request)
//      .then((e)=>{
//        console.log(e)
//      })
    }

  }
}
</script>

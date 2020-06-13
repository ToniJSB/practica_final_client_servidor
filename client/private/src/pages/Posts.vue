<template>
  <q-page class="flex flex-center">
    <div v-for="post in postsFilter" :key="post.idPost" v-bind="post" >

    <q-card dark bordered class="bg-grey-9 my-card">
      <q-card-section>
        <div class="text-h6"> {{post.title}} </div>
        <div class="text-subtitle2"> by: {{post.author.email}} </div>
      <q-separator dark inset />

        {{post.content}}
      </q-card-section>

      <q-card-section>
      <q-separator dark inset />
        {{ post.date }}

      <q-separator dark inset />
      
        <q-btn color="yellow" @click="updatePost(post.idPost)" icon-right="update" label="update" />
        <q-btn color="red" @click="deletePost(post.idPost)" icon-right="delete" label="delete" />

      </q-card-section>
    </q-card>

    </div>

  </q-page>
</template>

<script>
import { date } from 'quasar';
import PostList from '../components/PostList';
import { format } from 'url';
export default {
  name: 'Posts',
  components:{
      PostList
  },
  data() {
      return {
        posts: [],
        postsFilter: [],
        }
  },
  created: async function(){
      if(localStorage.getItem("token") == null || localStorage.getItem("token") == undefined ){
        console.log('entra')
        window.localStorage.setItem("token",this.$route.query.token) 
      }
      await this.getAll()
  },
  methods:{
    async getAll(){
      const request = {
        method:'GET',
        url:'http://localhost:8080/posts',
      }
      let promiseJson = await this.$axios(request)
      
      let posts = await promiseJson.data;
      posts.forEach((post) => {
        let jPost = JSON.parse(post)
        let dateFormated;
        if (date.isValid(jPost.date)){
            dateFormated = date.formatDate(jPost.date, 'dddd, DD-MM-YYYY')
        }
        let postOobject = {
          idPost: jPost.idPost,
          title: jPost.title,
          content: jPost.content,
          author: jPost.author,
          date: dateFormated
        };
        this.posts.push(postOobject);
      })
      this.postsFilter = this.posts
      console.log(this.postsFilter)
    },
    async deletePost(id){
      const request = {
        headers:{
          "Content-Type": "text/html"
        }
      }
      let axiosRequest = await this.$axios.get('localhost:8080/deletePost?idPost='+id,request)

      this.$route.push('posts')

    },
    async updatePost(id){
      this.$router.push('postForm?idPost='+id)
      console.log('update')
    }


  }

}
</script>

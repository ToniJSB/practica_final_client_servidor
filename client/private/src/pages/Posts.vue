<template>
  <q-page class="flex flex-center">
    <postList v-for="post in postsFilter" :key="post.idPost" v-bind="post" >

    </postList>
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
        postsFilter: []}
  },
  created: async function(){
      await this.getAll()
  },
  methods:{
    async getAll(){
      let promiseJson = await this.$axios.get('https://www.googleapis.com/blogger/v3/blogs/6332784932555712614/posts?key=AIzaSyB_qCla3aqrru4Y4n0UDrRr5xEOjHoXzc4')

      let posts = await promiseJson.data.items;
      posts.forEach((post) => {
        let dateFormated;
        if (date.isValid(post.published)){
            dateFormated = date.formatDate(post.published, 'dddd, DD-MM-YYYY')
        }
        let postOobject = {
          idPost: post.id,
          title: post.title,
          content: post.content,
          author: post.author.displayName,
          date: dateFormated
        };
        this.posts.push(postOobject);
      })
      this.postsFilter = this.posts
    }
  }

}
</script>

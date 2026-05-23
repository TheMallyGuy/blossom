<script lang="ts">
  import { fly } from "svelte/transition";
  import { cubicOut } from "svelte/easing";
</script>

<div 
  in:fly={{ y: 1080, duration: 600, easing: cubicOut }}
  class="flex h-screen w-full flex-col justify-between p-12 text-white"
>
  <div class="mx-auto mt-20 max-w-2xl">
    <h1 class="mb-4 text-4xl font-bold">WHO nâng cảnh báo dịch Ebola lên mức 'rất cao'</h1>
    <p class="text-lg leading-relaxed opacity-90">
      Tổ chức Y tế Thế giới (WHO) mới đây đã ban bố tình trạng khẩn cấp toàn diện đối với biến thể mới...
    </p>
  </div>
  
  <div class="animate-bounce text-center text-sm opacity-50">
    Press Down Arrow to Go Back
  </div>
</div>
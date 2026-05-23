<script lang="ts">
  import Clock from "$lib/components/Clock.svelte";
</script>

<div class="flex h-full w-full flex-col justify-between">
  <div class="flex flex-1 items-center justify-center">
    <Clock />
  </div>

  <div class="relative flex h-43.25 w-full items-end justify-end px-8 pb-6">
    <div
      class="absolute inset-0 bg-black/10 backdrop-blur-[2px] pointer-events-none"
    ></div>

    <!-- exported from figma -->
    <div
      class="absolute bottom-4 left-1/2 -translate-x-1/2 transform text-white/80 animate-bounce opacity-80"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="2.5"
        stroke="currentColor"
        class="h-6 w-6"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="m4.5 15.75 7.5-7.5 7.5 7.5"
        />
      </svg>
    </div>

    <!-- TODO : replace this widget with real data -->
    <div
      class="relative z-10 max-w-md text-right text-xs tracking-wide text-white drop-shadow-[0_2px_4px_rgba(0,0,0,0.8)]"
    >
      <h3 class="mb-1 text-sm font-semibold tracking-wider text-white">News</h3>
      <p class="font-medium leading-relaxed">
        WHO nâng cảnh báo dịch Ebola lên mức 'rất cao'
      </p>
      <p class="mt-0.5 opacity-80">
        Tổ chức Y tế Thế giới (WHO) nâng mức đánh giá rủi ro đối với chủng virus
        Ebola Bundibugyo tại Cộng hòa Dân chủ Congo lên mức 'rất cao' ở quy mô
        quốc gia, lo ngại nguy cơ bùng phát thành đại dịch.
      </p>
      <span class="mt-1 block text-[10px] italic opacity-60"
        >According to VNExpress</span
      >
    </div>
  </div>
</div>

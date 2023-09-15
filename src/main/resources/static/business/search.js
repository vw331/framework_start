$(function(){

    class Search {

        constructor() {
            this.load()
        }

        listWrap = $("#article-list")
        paginationWrap = $("#pagination-wrap")

        async load() {
            this.#setLoading();
            const { records, ...rest } = await this.#getData()
            const html = records.map(this.#renderItem).join(' ')
            const pagination = this.#renderPagination(rest)
            this.listWrap.empty().html(html);
            this.paginationWrap.empty().append(pagination);
        }

        async #getData() {
            await new Promise(resolve => setTimeout(resolve, 2000));
            return await $.get('/data/records.json')
        }

        #setLoading() {
            this.listWrap.empty().html(`<div class="d-flex justify-content-center">
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>`)
        }

        #renderItem(item) {
            const { no, source = 'rct' } = item
            const target = btoa(JSON.stringify({ no, source }));
            const link = ctxPath + `/article/${target}`
            return `
             <article class="post">
              <div class="card">
                <div class="card-body">
                  <div class="post-header">
                    <div class="post-category gap-1 text-line">
                      <span class="badge bg-primary rounded-pill">${item.no}</span>
                      <span class="badge bg-yellow rounded-pill">随机对照试验</span>
                      <span class="badge bg-ash text-white rounded-pill">麝香保心丸</span>
                      <span class="badge bg-ash text-white rounded-pill">急性冠脉综合征</span>
                      <span class="badge bg-ash text-white rounded-pill">心率变异</span>
                      <span class="badge bg-ash text-white rounded-pill">I24.901</span>
                    </div>
                    <h4 class="post-title mt-1 mb-0"><a target="_blank" href="${link}">${item.title_zh}</a></h4>
                    <p class="small">${item.title_en}</p>
                  </div>
                  <div class="post-content fs-sm">
                    <p class="mb-0">麝香保心丸;急性冠脉综合征;心率变异</p>
                    <p class="mb-0">急性冠脉综合征 (急性冠脉综合征)</p>
                    <p class="mb-0">心血管康复医学杂志</p>
                  </div>
                </div>
                <div class="card-footer">
                  <ul class="post-meta d-flex mb-0">
                    <li class="post-date"><i class="uil uil-calendar-alt"></i><span>2005/14/3/244~246</span></li>
                    <li class="post-author"><a href="#"><i class="uil uil-user"></i><span>吴剑萍;张俏红;戚本玲</span></a></li>
                  </ul>
                </div>
              </div>
            </article>
            `
        }

        #renderPagination({ pages, current, total }) {
            const range = [1,2, current -2, current -1, current, current +1, current +2, pages-2, pages-1, pages];
            const pageSet = new Set();
            if(current > 1){
                pageSet.add("上一页")
            }
            let index = 1;
            while (index <= pages) {
                if(range.includes(index)){
                    pageSet.add(index)
                }else if(index < current) {
                    pageSet.add("<<<")
                }else {
                    pageSet.add(">>>")
                }
                index++
            }
            if(current < pages){
                pageSet.add("下一页")
            }
            const liList = Array.from(pageSet).map(index => {
                return `
                    <li class="page-item ${index === current ? 'active': ''}">
                        <a class="page-link" href="javascript:void(0)" data-page="${index}">
                            ${index}
                        </a>
                      </li>
                `
            }).join('')
            const $ul = document.createElement('ul')
            $ul.className = 'pagination'
            $ul.innerHTML = liList;
            return $ul;
        }
    }

    new Search();

})
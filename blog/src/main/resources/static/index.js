const cached = {
    blogs: []
}
const baseUrl = "http://localhost:8080"

const API = async (url = "", method, payload) => {
    const res = await axios({
        url: baseUrl + url,
        method,
        data: payload
    });

    if (!res.data) return;

    setCached(res.data, "update")
    return res.data;
}

const setCached = (data, type) => {

    const findIndex = (arr, id) => {
        return arr.findIndex(blog => blog.id == id);
    }

    switch (type) {
        case "update":
            if (Array.isArray(data)) return cached.blogs = data;

            const idx = findIndex(cached.blogs, data.id);
            console.log(idx)
            if (idx === -1) cached.blogs.push(data);
            else cached.blogs[idx] = data;
            break;

        case "delete":
            const i =  findIndex(cached.blogs, data)
            cached.blogs.splice(i, 1);
            break;
    }

    render(cached.blogs);
}

const clearDOM = () => {
    $("#content").clear();

}

const app = async () => {
    const data = await API("/api/v1/blogs");
    render(data);
}

const render = (data) => {
    clearDOM()
    console.log(data)

    data.forEach(e => {
        const tr = $("tr").create();

        const tdUid = $("td").create();
        const tdDetail = $("td").create();
        const tdAuthor = $("td").create();
        const tdLove = $("td").create();
        const tdPostDate = $("td").create();
        const tdOption = $("td").create()


        tdUid.innerText = e.id;
        tdDetail.innerText = e.detail;
        tdAuthor.innerText = e.author;
        tdLove.innerText = e.love;
        tdPostDate.innerText = e.postDate;
        tdOption.innerHTML = `
                    <button onClick="edit(${e.id})" class="warning">Edit</button>
                    <button onClick="love(${e.id})" class="info">Love</button>
                `

        $(tr).append(tdUid, tdDetail, tdAuthor, tdLove, tdPostDate, tdOption)

        $("#content").append(tr)

    })
}

const $ = (el) => {
    const getId = () => document.querySelector(el);
    const getParent = () => {
        if (typeof el === "object") return el;
        if (typeof el === "string") return getId()
    }
    return {
        create: () => document.createElement(el),
        val: (val) => val || val === "" ? getId().value = val : getId().value,
        append: (...child) => {
            const parent = getParent();
            child.forEach(c => parent.appendChild(c));
        },
        clear: () => getId().innerHTML = "",
        _: () => getId(),
        text: (text) => text ? getId().innerText = text : getId().innerText,
        add: (c) => getId().classList.add(c),
        remove: (c) => getId().classList.remove(c),
        on: (evt, cb) => getId().addEventListener(evt, cb)
    }
}

const findBlog = (id) => {
    return cached.blogs.find(blog => blog.id === id);
}

const love = async (id) => {
    await API(`/api/v1/blogs/love/${id}`, "put");
}

const edit = (id) => {
    modal.open();

    const blog = findBlog(id);
    $("#blog_id").text(id);
    $("#edit_detail").val(blog.detail);
    $("#edit_author").val(blog.author);
    $("#edit_love").val(blog.love || "0");
    $("#edit_postDate").val(blog.postDate.split("T")[0]);
}

const modal = {
    open: () => {
        $("#modal").add("show");
        $("#backdrop").add("show");
    },
    close: () => {
        $("#modal").remove("show");
        $("#backdrop").remove("show");

        $("#blog_id").text("");
        $("#edit_detail").val("");
        $("#edit_author").val("");
        $("#edit_love").val("");
        $("#edit_postDate").val("");
    }
}

const listeners = () => {
    $("#cancel").on("click", () => {
        modal.close()
    })

    $("#save").on("click", async () => {
        const data = {
            id: ~~$("#blog_id").text(),
            detail: $("#edit_detail").val(),
            author: $("#edit_author").val(),
            love: ~~$("#edit_love").val(),
            postDate: $("#edit_postDate").val() + "T00:00:00"
        };

        await API(`/api/v1/blogs/${data.id}`, "put", data);

        modal.close()
    })

    $("#form").on("submit", async e => {
        e.preventDefault();

        const data = {
            detail: $("#new_detail").val(),
            author: $("#new_author").val()
        };

        await API("/api/v1/blogs", "post", data);
        $("#new_detail").val("");
        $("#new_author").val("");

    })

    $("#clear").on("click", () => {
        $("#new_detail").val("");
        $("#new_author").val("");

    })

    $("#delete").on("click", async () => {
        const id = $("#blog_id").text();
        modal.close();

        await API(`/api/v1/blogs/${id}`, "delete");
        setCached(id, "delete");
        console.log(id);

    })

    document.body.addEventListener("keyup", (e) => {
        if (e.key === "Escape") modal.close()
    })
}

(() => {
    app();
    listeners();
})()

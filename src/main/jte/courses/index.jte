@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
    content = @`
        <form action="/courses" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Поиск" />
        </form>
        @if(page.getCourses().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            @for(var course : page.getCourses())
               <div>
                   <h3><a href="/courses/${course.getId()}">${course.getName()}</a></h3>
                   <p>${course.getDescription()}</p>
               </div>
            @endfor
        @endif
    `
)
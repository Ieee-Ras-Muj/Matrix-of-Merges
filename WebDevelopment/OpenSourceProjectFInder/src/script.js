const projects = [
    { name: "Project A", language: "JavaScript", difficulty: "beginner", description: "A beginner-friendly JavaScript project." },
    { name: "Project B", language: "Python", difficulty: "intermediate", description: "An intermediate Python project for data analysis." },
    { name: "Project C", language: "Java", difficulty: "advanced", description: "An advanced Java project focusing on concurrency." },
];

document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('search-form');
    const projectsList = document.getElementById('projects-list');
    const featuredProjects = document.getElementById('featured-projects');

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const language = document.getElementById('language').value.toLowerCase();
        const difficulty = document.getElementById('difficulty').value.toLowerCase();
        
        const filteredProjects = projects.filter(project => 
            (language === '' || project.language.toLowerCase().includes(language)) &&
            (difficulty === '' || project.difficulty === difficulty)
        );

        displayProjects(filteredProjects, projectsList);
    });

    const randomProjects = getRandomProjects(projects, 3);
    displayProjects(randomProjects, featuredProjects);
});

function displayProjects(projects, container) {
    container.innerHTML = '';
    projects.forEach(project => {
        const projectCard = document.createElement('div');
        projectCard.classList.add('project-card');
        projectCard.innerHTML = `
            <h3>${project.name}</h3>
            <p>Language: ${project.language}</p>
            <p>Difficulty: ${project.difficulty}</p>
            <p>${project.description}</p>
        `;
        container.appendChild(projectCard);
    });
}

function getRandomProjects(projects, count) {
    const shuffled = projects.sort(() => 0.5 - Math.random());
    return shuffled.slice(0, count);
}

function sortProjects(projects, criteria) {
    return projects.sort((a, b) => a[criteria].localeCompare(b[criteria]));
}

async function fetchProjects() {
    return new Promise(resolve => {
        setTimeout(() => resolve(projects), 1000);
    });
}

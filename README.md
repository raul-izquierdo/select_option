# show_solutions

This Java application automates the creation of GitHub teams and the assignment of students to those teams based on a CSV roster exported from GitHub Classroom.

<!-- TODO: 📅 /**/ Uptade este doc -->


## Features

The main functionalities of this application include:
- Reads a CSV file with student identifiers and GitHub usernames.
- Creates GitHub teams based on the group code in the identifier.
- Adds students to their corresponding teams.
- Safe handling of GitHub API tokens (supports command-line, environment variable, or .env file).

## Usage

The jar can be downloaded from the [releases page](https://github.com/raul-izquierdo/show_solutions/releases).

```bash
java -jar show_solutions.jar <csv_file> -o <organization> -t <token>
```

### CSV File Format

The csv file is just the csv file exported from GitHub Classroom. For example, it can look like this:

```csv
"identifier","github_username","github_id","name"
"01-perez perez mariano","yaagma","",""
"02-gonzalez peon eduardo","edu23","",""
```

The only required columns are the first two: `identifier` and `github_username`.

The `identifier`, when the roster was created, it should have followed the format: `<group_id>-<name>` (e.g., `01-perez perez mariano`). This is the most important part as it determines the team creation and student assignment.

The teams are created based on the first two digits of the identifiers. For example, if there is an identifier `01-perez perez mariano`, a team named `G01` will be created, and every student with an identifier starting with `01-` will be added to this team.


## The Token

You can provide the GitHub API token in three ways (in order of precedence):
1. As a command-line argument: `-t <token>`
2. In a `.env` file
3. As an environment variable: `GITHUB_TOKEN`


## License

MIT

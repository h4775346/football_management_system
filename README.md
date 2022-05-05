# Football Management System Java Pure Console

# Classes

# Class Team

that Class Contains Array List Of The Players
And HashMap That Contains Enemy Teams
And How Many Times That Team Played
Against Them
(Because The Team Cant Play More Than Twice Against Any
Team At The League)
Has Many Function That Add More Players
and Update Them beside Team Information
Like Id and Scores


# Class Player

That Class Contains A Single Player Data
and a hashMap Contains Match As A key
And His Value Is The Number Of Goals That
Player Scored at this match


# Class Match

That Clsass Contains Match Data Like Id
And Date And The Team that Played At This
Match and Some Data Of The Match Like
Scores .....etc


# Class Start

I Can Call That Class Like Main Class
That Contains Most Of the Actions.
that Class That Display Menues And It
Contains ArrayList Of All Teams And an
ArrayList For Players That We Uset It To
Sort Players Depends On Their Scores
And Ranks.
And The Arralist Of All Held Matches.
And It Contains Too Many Functions
That Handle a lot of Errors Like Capital
Laters and Empty Lines and integers
and String Values .


Althougth It Contains Playing Match
Operations And Updating Teams Players
Matches And Every Thing Happens At
Start Class.


# Handelled Errors

All handled
Inputs Might Be Capital Or Small => handled

Integer Inputs Might be Litters => handled

Get The Whole line including Spaces => done

Empty Input Lines => handled

Team You Want Might Not Be Existed => handled

Player Have The Same Name And The Same T-Shirt Number => handled

Adding Players That Have Age Less That 16 and More Than 70 years old => handled

Match Id Might Not Be Existed => handled

Match Data Isnt The Same Formate Of The Date => handled

Id Match Change Every Match => done

Id Team Change Every Team Added => done

Every Match Check If The Team Has a GoalKeeper Or Not => done

There Might be Players Have The Same Name and The Same Team But Not The Same T-Shirt Number => done

Match Date Format Might Not Be Correct => handled

The Team Might Play Against himSelf => handled

Having More Than Goalkeeper At The Same Team Might Be A problem So If You Want To Swap goalKeeper Position With Another Player Change The Goalkeeper Positin First to Anything else And Then Give The The Goalkeeper Positin To Another Player => done

When You Change The GoalKeeper Position And Ask To Display Top 3 GoalKeepers, if There Was No Goal Keeper in the Team Then I Will Avoid This Team and If You Put Another Player Insteed Of Your Goal Keeper Then Player Received Goals Count Is Gonna Be 0 Score Unless You Play Using Him As A GoalKeeper IT Will Change As Goals Scored On Him, But When You Make Your GoalKeeper Back To His Position His Score Back With Him ^-^

Two Teams Play Against Ech other More Than Twice => handled

Update Players With Scored Goals More Than The Goals That Scored At The Match => handled

Searching For Match By Date There Might be A Leap Year => handled


Adding New Team Captain Or Update New one The Player Might Not Be In The Same Team  or There is a Player Has The Same Name At The Same Team So Who Do You Think You Choosed For The Captain Position => handled


The Team Must Not be Less Than 11 Player Having Goal and Captain => handled

Changing Team or Match Id Might be a Problem => done

Changing Scored Goals In Team Data Is a Problem Because You Have To Say Whose Scored Theos Goals And Whose Lost The Same Goals To And What And Change Match Data to and That Will Cause Hard Problem => Deleted

Every Time We Add Player We Change The Average Age Of Team Players => done

On Adding Player Check If His Number Existed Or Not => done

Back To The Main Menue Everytime You Type (" back ") => Added
